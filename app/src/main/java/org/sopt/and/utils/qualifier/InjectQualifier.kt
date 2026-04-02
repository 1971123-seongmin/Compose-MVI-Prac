package org.sopt.and.utils.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthRequired

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthNotRequired