//
//  DialogQueue.swift
//  iosApp
//
//  Created by Mitch Tabian on 2021-03-30.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class DialogQueue: ObservableObject {
    
    @Published var queue: Queue<GenericDialogInfo> = Queue()
    
    @Published var hasMessages = false
    
    func removeHeadMessage(){
        queue.poll()
        checkSize()
    }
    
    func appendMessage(title: String, description: String){
        queue.offer(
            GenericDialogInfo.Builder()
                .title(title: title)
                .description(description: description)
                .onDismiss {
                    self.removeHeadMessage()
                }
                .positive(
                    positiveAction: PositiveAction(
                        positiveBtnTxt: "Ok",
                        onPositiveAction: {
                            self.removeHeadMessage()
                        })
                )
                .build()
        )
        checkSize()
    }
    
    private func checkSize(){
        if(queue.count > 0){
            hasMessages = true
        }
        else{
            hasMessages = false
        }
    }
}


