package com.tayyibia.academy

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest

val supabase = createSupabaseClient(
    supabaseUrl = "https://bsnsgmnsuyvgkzclscjm.supabase.co",
    supabaseKey = "sb_publishable_m7yGcs2ulA9GgrLyVyK8Og_SZDU5uFN"
) {
    install(Auth)
    install(Postgrest)
}
