import shared
import SwiftUI

@main
struct iOSApp: App {
    init() {
        KoinHelperKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            HomeScreen()
        }
    }
}
