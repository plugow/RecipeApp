package com.plugow.recipeapp.util.errorhandling

sealed class Failure {

    object NetworkConnection: Failure()
    object ServerError: Failure()
    object Unauthorized: Failure()
    object ServiceUnavailable: Failure()
    object NotFound: Failure()
    object TimeOut: Failure()
    object Unknown: Failure()
    object Forbidden: Failure()

    /** * Extend this class for feature specific failures.*/
    open class FeatureFailure(): Failure()
}