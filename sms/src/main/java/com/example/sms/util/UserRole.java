package com.example.sms.util;

/** Enum representing the roles of administrators in the application. * This
 * enum is used to differentiate between different levels of administrative
 * access, such as super administrators and branch administrators. */
public enum UserRole {
    SYSTEM_ADMIN,
    SCHOOL_GROUP_ADMIN,
    BRANCH_ADMIN,
    TEACHER,
    PRINCIPAL,
    ACCOUNTANT,
    LIBRARIAN,
    SUPPORT,
    RECEPTIONIST
//    STUDENT,
//    PARENT
}
