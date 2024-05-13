//
//  MovieDetailScreen.swift
//  iosApp
//
//  Created by Otamurod Safarov on 13/05/24.
//  Copyright © 2024. All rights reserved.
//

import shared
import SwiftUI

struct MovieDetailScreen: View {
    let movie: Movie

    var body: some View {
        ScrollView {
            VStack {
                ZStack {
                    AsyncImage(url: URL(string: movie.imageUrl)) { image in
                        image.resizable().scaledToFill()
                    } placeholder: {
                        ProgressView()
                    }
                }
                .frame(maxWidth: .infinity, minHeight: 300, maxHeight: 300)

                VStack(alignment: .leading, spacing: 12) {
                    Text(movie.title)
                        .font(.title)
                        .fontWeight(.bold)
                        .fixedSize(horizontal: false, vertical: true)

                    Button(action: {}) {
                        HStack {
                            Image(systemName: "play.fill")
                                .foregroundColor(.white)

                            Text("Start watching now")
                                .foregroundColor(.white)
                        }
                    }.frame(maxWidth: .infinity)
                        .padding()
                        .background(.red)
                        .clipShape(RoundedRectangle(cornerRadius: 50))

                    Text("Released in \(movie.releaseDate)".uppercased())

                    Text(movie.description_).font(.body)
                        .fixedSize(horizontal: false, vertical: true)

                }.padding()
                    .background(.background)
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
        }
    }
}

struct MovieDetailScreen_Previews: PreviewProvider {
    static var previews: some View {
        MovieDetailScreen(movie: sampleMovie)
    }
}
