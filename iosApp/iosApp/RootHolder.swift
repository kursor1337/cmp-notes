import SwiftUI
import ComposeApp

final class RootHolder: ObservableObject {
    let rootComponent: RootComponent
    
    init() {
        let buildType: BuildType
        let backend: Backend
        
        switch EnvironmentService.shared.currentEnvironment {
        case .debug:
            buildType = .debug
            backend = .development
        case .release:
            buildType = .theRelease
            backend = .production
        }
        
        let configuration = Configuration(
            platform: Platform(),
            buildType: buildType,
            backend: backend
        )
        
        let sharedApplication = SharedApplication(configuration: configuration)
        
        /// Создание контекста компонента с жизненным циклом, обработчиком состояния и обработчиком возврата.
        let defaultComponentContext = DefaultComponentContext(
            lifecycle: ApplicationLifecycle(),
            stateKeeper: nil,
            instanceKeeper: nil,
            backHandler: BackDispatcherService.shared.backDispatcher
        )
        
        /// Инициализация корневого компонента с использованием созданного контекста.
        rootComponent = sharedApplication.createRootComponent(componentContext: defaultComponentContext)
    }
}
