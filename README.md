# DAR UL ULOOM TAYYIBIA TAHIRIA ACADEMY

Urdu-first Android starter project using Kotlin + Jetpack Compose, with a Supabase SQL starter schema.

## Important
This is source code, not a built APK and not a live connected backend. You must create a Supabase project, apply `supabase/schema.sql`, and add your Supabase URL and anon key before authentication can work.

## Admin email
`bsft3001@gmail.com` is the intended initial administrator email. Create this user in Supabase Authentication yourself, then assign the `super_admin` role in the `profiles` table. Do not put passwords in source code.

## Setup
1. Open this folder in Android Studio (recent stable version).
2. Create a Supabase project at https://supabase.com.
3. In Supabase SQL Editor, run `supabase/schema.sql`.
4. In Supabase Authentication, create/invite `bsft3001@gmail.com` and complete email verification.
5. Add the project URL and anon key in `local.properties` (see `local.properties.example`).
6. Build and run from Android Studio.

The current app screen is a UI starter. Supabase Auth wiring is intentionally left as a clearly marked integration step because project credentials are private and have not been supplied.
