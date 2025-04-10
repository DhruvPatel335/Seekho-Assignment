# Anime Explorer App

A simple Android application that fetches and displays a list of anime series using the Jikan API. The app allows users to browse top anime, view detailed information, and watch trailers if available.

---

## Features Implemented

### Anime List Page (Home Screen)
- Fetches a list of top-rated anime using:  
  `https://api.jikan.moe/v4/top/anime`
- Displays:
    - Anime **Title**
    - **Number of Episodes**
    - **Rating** (MyAnimeList score)
    - **Poster Image**

### Anime Detail Page
- Triggered when an anime is selected from the list.
- Fetches anime-specific details using:  
  `https://api.jikan.moe/v4/anime/{anime_id}`
- Displays:
    - Embedded **video player** to play the trailer (if available)
    - If trailer is not available, displays the **poster image**
    - **Title**
    - **Synopsis/Plot**
    - **Genres**
    - **Main Cast** (limited to top listed characters)
    - **Number of Episodes**
    - **Rating**

---

## Assumptions Made
- Only the **top-rated anime list** is shown on the Home screen (pagination not implemented).
- The **embedded YouTube trailer URL** in the API response is used as the trailer video.
- **Main cast** refers to the broadcast section from the anime details endpoint. A simplified version is displayed.
- The app assumes a stable network connection for API and video streaming.

---

## Known Limitations
- **Pagination** for loading more anime on scroll is not yet implemented.
- **Search functionality** is not included in this version.
- Some anime may not have trailer videos; in that case, only poster images are shown.
- **Offline support** and **data caching** are not available in this version.
- **Error handling** for slow or failed network requests is minimal.

---