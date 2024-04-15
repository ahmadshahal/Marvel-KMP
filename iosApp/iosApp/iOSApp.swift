import SwiftUI

@main
struct iOSApp: App {

    init() {
        KoinHelperiosKt.initKoin()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}