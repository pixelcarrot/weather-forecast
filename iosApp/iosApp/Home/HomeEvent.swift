enum HomeEvent {
    case load
    case noLocation
    case receiveLocation(lat: Double, lon: Double)
    case noPermission
}
