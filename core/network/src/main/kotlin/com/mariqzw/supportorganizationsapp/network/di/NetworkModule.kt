package com.mariqzw.supportorganizationsapp.network.di

import com.mariqzw.supportorganizationsapp.network.authservice.AuthService
import com.mariqzw.supportorganizationsapp.network.authservice.KtorAuthService
import com.mariqzw.supportorganizationsapp.network.authservice.usecase.RefreshTokenUseCaseImpl
import com.mariqzw.supportorganizationsapp.network.authservice.usecase.RegisterUseCaseImpl
import com.mariqzw.supportorganizationsapp.network.authservice.usecase.SignInUseCaseImpl
import com.mariqzw.supportorganizationsapp.common.di.SaDispatchers
import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.AssigneeApplicationUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.CancelApplicationUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.CompleteApplicationUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.CreateApplicationUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.DeleteApplicationUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.GetAllApplicationsByCompanionUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.GetAllApplicationsUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.GetAllNewWithoutCompanionUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.GetApplicationByIdUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.RejectApplicationUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.authservice.RefreshTokenUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.authservice.RegisterCompanionUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.authservice.RegisterUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.authservice.SignInUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.userservice.GetUserByNameUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.userservice.UpdateUserUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.userservice.CreateApplicationsUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.userservice.CancelApplicationsUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.userservice.GetListOfApplicationsUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.userservice.GetUserProfileUseCase
import com.mariqzw.supportorganizationsapp.network.applicationservice.ApplicationService
import com.mariqzw.supportorganizationsapp.network.applicationservice.KtorApplicationService
import com.mariqzw.supportorganizationsapp.network.applicationservice.usecase.AssigneeApplicationUseCaseImpl
import com.mariqzw.supportorganizationsapp.network.applicationservice.usecase.CompleteApplicationUseCaseImpl
import com.mariqzw.supportorganizationsapp.network.applicationservice.usecase.DeleteApplicationUseCaseImpl
import com.mariqzw.supportorganizationsapp.network.applicationservice.usecase.GetAllApplicationsUseCaseImpl
import com.mariqzw.supportorganizationsapp.network.applicationservice.usecase.GetApplicationByIdUseCaseImpl
import com.mariqzw.supportorganizationsapp.network.applicationservice.usecase.RejectApplicationUseCaseImpl
import com.mariqzw.supportorganizationsapp.network.applicationservice.usecase.CreateApplicationUseCaseImpl
import com.mariqzw.supportorganizationsapp.network.applicationservice.usecase.CancelApplicationUseCaseImpl
import com.mariqzw.supportorganizationsapp.network.applicationservice.usecase.GetAllApplicationsByCompanionUseCaseImpl
import com.mariqzw.supportorganizationsapp.network.applicationservice.usecase.GetAllNewWithoutCompanionUseCaseImpl
import com.mariqzw.supportorganizationsapp.network.authservice.usecase.RegisterCompanionUseCaseImpl
import com.mariqzw.supportorganizationsapp.network.userservice.KtorUserService
import com.mariqzw.supportorganizationsapp.network.userservice.UserService
import com.mariqzw.supportorganizationsapp.network.userservice.usecase.GetUserByNameUseCaseImpl
import com.mariqzw.supportorganizationsapp.network.userservice.usecase.UpdateUserUseCaseImpl
import com.mariqzw.supportorganizationsapp.network.userservice.usecase.CreateApplicationsUseCaseImpl
import com.mariqzw.supportorganizationsapp.network.userservice.usecase.CancelApplicationsUseCaseImpl
import com.mariqzw.supportorganizationsapp.network.userservice.usecase.GetListOfApplicationsUseCaseImpl
import com.mariqzw.supportorganizationsapp.network.userservice.usecase.GetUserProfileUseCaseImpl
import com.mariqzw.supportorganizationsapp.network.tokenservice.TokenSupport
import com.mariqzw.supportorganizationsapp.network.tokenservice.TokenSupportImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module
import kotlin.time.Duration.Companion.seconds

val provideNetworkModule = module {
    single(named("network")) {
        Json { ignoreUnknownKeys = true }
    }

    single {
        OkHttp.create()
    }

    single {
        val json = get<Json>(named("network"))
        val engine = get<HttpClientEngine>()

        HttpClient(engine) {
            install(ContentNegotiation) { json(json) }

            install(HttpTimeout) {
                connectTimeoutMillis = 20.seconds.inWholeMilliseconds
                requestTimeoutMillis = 60.seconds.inWholeMilliseconds
                socketTimeoutMillis = 20.seconds.inWholeMilliseconds
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
        }
    }

    single<AuthService> {
        KtorAuthService(
            client = get(),
            apiHost = get(named("API")),
            dispatcher = get(named(SaDispatchers.IO.name)),
            authenticationDataStore = get()
        )
    }

    single<UserService> {
        KtorUserService(
            client = get(),
            apiHost = get(named("API")),
            dispatcher = get(named(SaDispatchers.IO.name))
        )
    }

    single<ApplicationService> {
        KtorApplicationService(
            client = get(),
            apiHost = get(named("API")),
            dispatcher = get(named(SaDispatchers.IO.name))
        )
    }

    single<TokenSupport> {
        TokenSupportImpl(
            userAuthDataStore = get(),
            refreshTokenUseCase = get()
        )
    }

    single<SignInUseCase> {
        SignInUseCaseImpl(authService = get())
    }

    single<RegisterUseCase> {
        RegisterUseCaseImpl(authService = get())
    }

    single<RegisterCompanionUseCase> {
        RegisterCompanionUseCaseImpl(authService = get())
    }

    single<RefreshTokenUseCase> {
        RefreshTokenUseCaseImpl(authService = get())
    }

    single<UpdateUserUseCase> {
        UpdateUserUseCaseImpl(
            userService = get(),
            tokenSupport = get()
        )
    }

    single<CreateApplicationsUseCase> {
        CreateApplicationsUseCaseImpl(userService = get())
    }

    single<CancelApplicationsUseCase> {
        CancelApplicationsUseCaseImpl(userService = get())
    }

    single<GetUserByNameUseCase> {
        GetUserByNameUseCaseImpl(userService = get())
    }

    single<GetListOfApplicationsUseCase> {
        GetListOfApplicationsUseCaseImpl(userService = get())
    }

    single<GetUserProfileUseCase> {
        GetUserProfileUseCaseImpl(
            userService = get(),
            tokenSupport = get()
        )
    }

    single<GetApplicationByIdUseCase> {
        GetApplicationByIdUseCaseImpl(applicationService = get())
    }

    single<DeleteApplicationUseCase> {
        DeleteApplicationUseCaseImpl(
            applicationService = get(),
            tokenSupport = get())
    }

    single<GetAllApplicationsUseCase> {
        GetAllApplicationsUseCaseImpl(
            applicationService = get(),
            tokenSupport = get()
        )
    }

    single<GetAllApplicationsByCompanionUseCase> {
        GetAllApplicationsByCompanionUseCaseImpl(
            applicationService = get(),
            tokenSupport = get()
        )
    }

    single<GetAllNewWithoutCompanionUseCase> {
        GetAllNewWithoutCompanionUseCaseImpl(
            applicationService = get(),
            tokenSupport = get()
        )
    }

    single<CreateApplicationUseCase> {
        CreateApplicationUseCaseImpl(
            applicationService = get(),
            tokenSupport = get()
        )
    }

    single<RejectApplicationUseCase> {
        RejectApplicationUseCaseImpl(
            applicationService = get(),
            tokenSupport = get()
        )
    }

    single<CompleteApplicationUseCase> {
        CompleteApplicationUseCaseImpl(
            applicationService = get(),
            tokenSupport = get()
        )
    }

    single<CancelApplicationUseCase> {
        CancelApplicationUseCaseImpl(
            applicationService = get(),
            tokenSupport = get()
        )
    }

    single<AssigneeApplicationUseCase> {
        AssigneeApplicationUseCaseImpl(
            applicationService = get(),
            tokenSupport = get()
        )
    }
}
