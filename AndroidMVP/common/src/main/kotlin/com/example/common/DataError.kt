package com.example.common

/**
 * We use DataError as basis and we can extend this to have a unique error per feature
 */
sealed class DataError {
    object GENERIC_ERROR : DataError()
}
