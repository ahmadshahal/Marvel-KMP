import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        InitHelper_iosKt.doInit()
    }

	var body: some Scene {
		WindowGroup {
			ContentView().ignoresSafeArea()
		}
	}
}