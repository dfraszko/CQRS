import com.google.inject.AbstractModule
import org.talend.training.command.DatasetEntitySupervisor
import org.talend.training.projections.ViewUpdater
import org.talend.training.query.{Repository, SlickH2Repository}
import play.api.libs.concurrent.AkkaGuiceSupport

/**
  * This class is a Guice module that tells Guice how to bind several
  * different types. This Guice module is created when the Play
  * application starts.

  * Play will automatically use any class called `Module` that is in
  * the root package. You can create modules in other locations by
  * adding `play.modules.enabled` settings to the `application.conf`
  * configuration file.
  */
class Module extends AbstractModule with AkkaGuiceSupport {

  // https://www.playframework.com/documentation/2.6.x/ScalaDependencyInjection#programmatic-bindings
  override def configure() = {
    bind(classOf[Repository]).to(classOf[SlickH2Repository])
    bindActor[DatasetEntitySupervisor]("dataset-supervisor")
    bindActor[ViewUpdater]("view-updater")
  }

}
