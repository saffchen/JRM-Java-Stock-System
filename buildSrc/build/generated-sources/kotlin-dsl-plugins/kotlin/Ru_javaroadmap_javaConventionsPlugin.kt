/**
 * Precompiled [ru.javaroadmap.java-conventions.gradle.kts][Ru_javaroadmap_java_conventions_gradle] script plugin.
 *
 * @see Ru_javaroadmap_java_conventions_gradle
 */
class Ru_javaroadmap_javaConventionsPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("Ru_javaroadmap_java_conventions_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
