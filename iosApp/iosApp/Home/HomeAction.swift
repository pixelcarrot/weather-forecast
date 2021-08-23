enum HomeAction {
    case load
    case noLocation
    case receiveLocation(lat: Double, lon: Double)
    case noPermission
}
