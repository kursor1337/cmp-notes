import SwiftUI
import ComposeApp

final class RootHolder: ObservableObject {
    let rootComponent: FeaturesRootComponent
    
    init() {
        let buildType: CoreBuildType
        let backend: CoreBackend
        
        switch EnvironmentService.shared.currentEnvironment {
        case .debug:
            buildType = .debug
            backend = .development
        case .release:
            buildType = .release_
            backend = .production
        }
        
        let configuration = CoreConfiguration(
            platform: CorePlatform(),
            buildType: buildType,
            backend: backend
        )
        
        let sharedApplication = SharedApplication(configuration: configuration)
        
        /// Создание контекста компонента с жизненным циклом, обработчиком состояния и обработчиком возврата.
        let defaultComponentContext = DefaultComponentContext(
            lifecycle: ApplicationLifecycle(),
            stateKeeper: nil,
            instanceKeeper: nil,
            backHandler: nil
        )
        
        /// Инициализация корневого компонента с использованием созданного контекста.
        rootComponent = sharedApplication.createRootComponent(componentContext: defaultComponentContext)
    }
}
