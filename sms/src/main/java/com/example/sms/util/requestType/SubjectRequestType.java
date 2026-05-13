package com.example.sms.util.requestType;

/** Enum representing the types of subject-related requests in the application.
 * This enum is used to differentiate between various operations related to
 * subjects, such as creating a new subject, finding a subject by its ID,
 * retrieving all subjects, updating a subject's information, and deleting a
 * subject. */
public enum SubjectRequestType {
    
    CREATE,
    FIND_BY_ID,
    FIND_ALL,
    UPDATE,
    DELETE
}
