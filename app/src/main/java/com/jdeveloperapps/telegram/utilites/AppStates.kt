package com.jdeveloperapps.telegram.utilites

enum class AppStates(val state : String) {
    ONLINE("в сети"),
    OFFLINE("был недавно"),
    TYPING("печатает");

    companion object{
        fun updateStates(appState: AppStates) {
            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_STATUS)
                .setValue(appState.state)
                .addOnSuccessListener { USER.status = appState.state }
                .addOnFailureListener { showToast(it.message.toString()) }
        }
    }
}