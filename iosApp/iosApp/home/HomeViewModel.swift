//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Otamurod Safarov on 12/05/24.
//  Copyright © 2024. All rights reserved.
//

import Foundation
import shared
import SwiftUI

extension HomeScreen {
    @MainActor class HomeViewModel: ObservableObject {
        private let getMoviesUseCase = GetMoviesUseCase()
        
        @Published
        private(set) var movies: [Movie] = []
        @Published
        private(set) var isLoading: Bool = false
        
        private var currentPage = 1
        private(set) var isLoadingFinished: Bool = false
        
        func loadMovies() async {
            if isLoading {
                return
            }
            
            do {
                let movies = try await getMoviesUseCase.invoke(page: Int32(currentPage))
                
                isLoading = false
                isLoadingFinished = movies.isEmpty
                
                currentPage += 1
                self.movies = self.movies + movies
            } catch {
                isLoading = false
                isLoadingFinished = true
                
                print(error.localizedDescription)
            }
        }
    }
}
