import com.android.build.gradle.LibraryExtension
import com.mariqzw.supportorganizationsapp.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("supportorganizationsapp.android.library")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            configureAndroidCompose(extensions.getByType<LibraryExtension>())
        }
    }
}
