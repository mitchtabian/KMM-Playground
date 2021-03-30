//
//  SearchAppBar.swift
//  iosApp
//
//  Created by Mitch Tabian on 2021-03-24.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

@available(iOS 14.0, *)
struct SearchAppBar: View {
    
//    @ObservedObject var viewModel: RecipeListViewModel
    
//    init(viewModel: RecipeListViewModel){
//        self.viewModel = viewModel
//    }
    
    @State var query: String = ""
    let categories: [FoodCategory]
    let setQuery: (String) -> Void
    let newSearchEvent: () -> Void
    let onSelectedCategoryChanged: (String) -> Void
    
    init(
        categories: [FoodCategory],
        setQuery: @escaping (String) -> Void,
        newSearchEvent: @escaping () -> Void,
        onSelectedCategoryChanged: @escaping (String) -> Void
    ) {
        self.categories = categories
        self.setQuery = setQuery
        self.newSearchEvent = newSearchEvent
        self.onSelectedCategoryChanged = onSelectedCategoryChanged
    }
    
    var body: some View {
        VStack{
            HStack{
                Image(systemName: "magnifyingglass")
                TextField(
                    "Search...",
                    text: $query,
                    onCommit:{
                        newSearchEvent()
                    }
                )
                .onChange(of: query, perform: { value in
                    setQuery(value)
                })
                
            }
            .padding(.bottom, 8)
            ScrollView(.horizontal){
                HStack(spacing: 10){
                    ForEach(categories, id: \.self){ category in
                        FoodCategoryChip(category: category.value)
                        .onTapGesture {
                            query = category.value
                            onSelectedCategoryChanged(category.value)
                            newSearchEvent()
                        }
                    }
                }
            }
        }
        .padding(.top, 8)
        .padding(.leading, 8)
        .padding(.trailing, 8)
        .background(Color.white.opacity(0))
    }
}

@available(iOS 14.0, *)
struct SearchAppBar_Previews: PreviewProvider {
    static var previews: some View {
        SearchAppBar(
            categories: [],
            setQuery: { query  in
                
            },
            newSearchEvent: {
                
            },
            onSelectedCategoryChanged: {categroy in
                
            })
    }
}
