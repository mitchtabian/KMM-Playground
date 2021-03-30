//
//  ApiTokenProvider.swift
//  iosApp
//
//  Created by Mitch Tabian on 2021-03-30.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation


protocol ApiTokenProtocol {
    func provideToken() -> String
}
class ApiTokenProvider: ApiTokenProtocol {
    
    func provideToken() -> String {
        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }
}
