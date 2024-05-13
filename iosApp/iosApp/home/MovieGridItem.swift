//
//  MovieGridItem.swift
//  iosApp
//
//  Created by Otamurod Safarov on 13/05/24.
//  Copyright © 2024. All rights reserved.
//

import shared
import SwiftUI

struct MovieGridItem: View {
    let movie: Movie

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            ZStack {
                AsyncImage(url: URL(string: movie.imageUrl)) { image in
                    image.resizable()
                } placeholder: {
                    Color.gray
                    ProgressView()
                        .tint(.blue)
                        .scaleEffect(x: 3, y: 3, anchor: .center)
                }

                Circle()
                    .frame(width: 50, height: 50, alignment: .center)
                    .foregroundColor(.black.opacity(0.7))

                Image(systemName: "play.fill")
                    .foregroundColor(.white)

            }.frame(maxWidth: .infinity, idealHeight: .infinity)

            Text(movie.title)
                .font(.title3)
                .fontWeight(.bold)
                .lineLimit(1)
                .padding(5)

            Text(movie.releaseDate)
                .font(.caption)
                .padding(5)

        }.frame(maxWidth: .infinity, maxHeight: 260)
            .background(.background)
            .clipShape(RoundedRectangle(cornerRadius: 8))
    }
}

struct MovieGridItem_Previews: PreviewProvider {
    static var previews: some View {
        MovieGridItem(movie: sampleMovie)
            .previewInterfaceOrientation(.portrait)
    }
}
