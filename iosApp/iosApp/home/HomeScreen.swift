import shared
import SwiftUI

struct HomeScreen: View {
    @StateObject var viewModel = HomeViewModel()

    let gridColumns: [GridItem] = Array(repeating: GridItem(.flexible(), spacing: 16), count: 2)

    var body: some View {
        NavigationView {
            ScrollView {
                LazyVGrid(columns: gridColumns, spacing: 16) {
                    ForEach(viewModel.movies, id: \.id) { movie in
                        NavigationLink(destination: MovieDetailScreen(movie: movie)) {
                            MovieGridItem(movie: movie)
                                .task {
                                    if movie == viewModel.movies.last && !viewModel.isLoading && !viewModel.isLoadingFinished {
                                        await viewModel.loadMovies()
                                    }
                                }
                        }.buttonStyle(PlainButtonStyle())
                    }

                    if viewModel.isLoading {
                        Section(footer: ProgressView()) {}
                    }
                }
                .padding()
                .background(.blue.opacity(0.2))
            }
            .navigationTitle("Movies")

        }.task {
            await viewModel.loadMovies()
        }
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
