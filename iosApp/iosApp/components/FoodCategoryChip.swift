//
//  FoodCategoryChip.swift
//  iosApp
//
//  Created by Mitch Tabian on 2021-03-24.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct FoodCategoryChip: View {
    let category: String
    var body: some View {
        HStack{
            DefaultText(category, size: 16)
                .padding(8)
                .background(Color.blue)
                .foregroundColor(.white)
        }
        .cornerRadius(10)
    }
}

struct FoodCategoryChip_Previews: PreviewProvider {
    static var previews: some View {
        FoodCategoryChip(category: "Chicken")
    }
}
