import SwiftUI

@main
struct iOSApp: App {

    init() {
        InitHelperIosKt.init()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}