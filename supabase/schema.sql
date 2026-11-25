-- Run in Supabase SQL Editor.
-- Creates a minimal secure foundation. Extend policies and tables after reviewing roles.

create extension if not exists pgcrypto;

create table if not exists public.profiles (
  id uuid primary key references auth.users(id) on delete cascade,
  email text unique not null,
  full_name text,
  role text not null default 'teacher'
    check (role in ('super_admin','admin','teacher')),
  is_active boolean not null default true,
  created_at timestamptz not null default now()
);

create table if not exists public.students (
  id uuid primary key default gen_random_uuid(),
  full_name text not null,
  guardian_name text,
  guardian_phone text,
  class_name text,
  address text,
  is_active boolean not null default true,
  created_at timestamptz not null default now()
);

create table if not exists public.teachers (
  id uuid primary key default gen_random_uuid(),
  full_name text not null,
  phone text,
  subject text,
  is_active boolean not null default true,
  created_at timestamptz not null default now()
);

create table if not exists public.teacher_attendance (
  id uuid primary key default gen_random_uuid(),
  teacher_id uuid not null references public.teachers(id),
  attendance_date date not null,
  status text not null check (status in ('present','absent','leave')),
  marked_by uuid not null references auth.users(id),
  created_at timestamptz not null default now(),
  unique (teacher_id, attendance_date)
);

create table if not exists public.student_attendance (
  id uuid primary key default gen_random_uuid(),
  student_id uuid not null references public.students(id),
  attendance_date date not null,
  status text not null check (status in ('present','absent','leave')),
  marked_by uuid not null references auth.users(id),
  created_at timestamptz not null default now(),
  unique (student_id, attendance_date)
);

create table if not exists public.quran_progress (
  id uuid primary key default gen_random_uuid(),
  student_id uuid not null references public.students(id),
  progress_date date not null default current_date,
  sabaq text,
  sabqi text,
  manzil text,
  para_number integer check (para_number between 1 and 30),
  notes text,
  recorded_by uuid not null references auth.users(id),
  created_at timestamptz not null default now()
);

create table if not exists public.fee_records (
  id uuid primary key default gen_random_uuid(),
  student_id uuid not null references public.students(id),
  fee_month date not null,
  amount numeric(12,2) not null check (amount >= 0),
  paid_amount numeric(12,2) not null default 0 check (paid_amount >= 0),
  receipt_no text,
  recorded_by uuid not null references auth.users(id),
  created_at timestamptz not null default now()
);

alter table public.profiles enable row level security;
alter table public.students enable row level security;
alter table public.teachers enable row level security;
alter table public.teacher_attendance enable row level security;
alter table public.student_attendance enable row level security;
alter table public.quran_progress enable row level security;
alter table public.fee_records enable row level security;

-- Helper: only active admins/super-admins may manage records.
create or replace function public.current_user_role()
returns text language sql stable security definer
set search_path = public
as $$
  select role from public.profiles
  where id = auth.uid() and is_active = true
$$;

create policy "users read own profile" on public.profiles
for select to authenticated using (id = auth.uid());

create policy "admins read profiles" on public.profiles
for select to authenticated using (public.current_user_role() in ('super_admin','admin'));

-- Admin-only operational data policies.
do $$
declare t text;
begin
  foreach t in array array['students','teachers','teacher_attendance','student_attendance','quran_progress','fee_records']
  loop
    execute format(
      'create policy %I on public.%I for all to authenticated using (public.current_user_role() in (''super_admin'',''admin'')) with check (public.current_user_role() in (''super_admin'',''admin''))',
      'admins manage ' || t, t
    );
  end loop;
end $$;

-- Teacher attendance is intentionally admin-managed only.
-- Do not expose the service_role key in the Android app.
