package com.example.sms.util.requestType;

/** * Enum representing the types of student-related requests in the application.
 * This enum is used to differentiate between various operations related to
 * student management, such as creating a student, finding a student by ID,
 * updating student information, deleting a student, enrolling in a course, and
 * withdrawing from a course.
 */
public enum StudentRequestType {
    CREATE,
    FIND_BY_ID,
    UPDATE,
    DELETE,
    ENROLL_IN_COURSE,
    WITHDRAW_FROM_COURSE
}
