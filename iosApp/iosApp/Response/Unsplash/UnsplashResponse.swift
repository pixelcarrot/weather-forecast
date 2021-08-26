struct UnsplashResponse: Decodable {
    var urls: UrlsResponse
    var user: UserResponse
    var links: LinksResponse
}
