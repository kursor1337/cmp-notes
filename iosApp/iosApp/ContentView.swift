import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    
    let rootComponent: RootComponent
    
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(rootComponent)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}



