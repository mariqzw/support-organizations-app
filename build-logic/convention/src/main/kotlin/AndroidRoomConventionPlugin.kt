import com.mariqzw.supportorganizationsapp.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidRoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
            }

            dependencies {
                add("implementation", libs.findLibrary("androidx-room-ktx").get())
                add("implementation", libs.findLibrary("androidx-room-runtime").get())
                add("ksp", libs.findLibrary("androidx-room-compiler").get())

                add("testImplementation", libs.findLibrary("androidx-room-testing").get())  // For unit tests
                add("androidTestImplementation", libs.findLibrary("androidx-room-testing").get()) // For instrumental tests
            }
        }
    }
}
