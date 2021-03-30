/*
    First-in first-out queue (FIFO)
    New elements are added to the end of the queue. Dequeuing pulls elements from
    the front of the queue.
    Enqueuing is an O(1) operation, dequeuing is O(n). Note: If the queue had been
    implemented with a linked list, then both would be O(1).

    Tried to make it as similar as possible to java.util Queue:
    https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html
*/
public struct Queue<T> {
    fileprivate var array = [T]()

    public var count: Int {
        return array.count
    }

    public var isEmpty: Bool {
        return array.isEmpty
    }

    public mutating func add(_ element: T) {
        array.append(element)
    }

    public mutating func remove() throws -> T {
        if isEmpty {
            throw QueueError.NoSuchElementException
        } else {
            return array.removeFirst()
        }
    }
    
    public func element() throws -> T  {
        guard let first = array.first else{
            throw QueueError.NoSuchElementException
        }
        return first
    }
    
    public mutating func offer(_ element: T) -> Bool {
        do{
            try array.append(element)
        }catch{
            return false
        }
        return true
    }
    
    public mutating func poll() -> T? {
        if isEmpty {
            return nil
        } else {
            return array.removeFirst()
        }
    }

    public func peek() -> T? {
        if isEmpty {
            return nil
        } else {
            return array.first
        }
    }
    
}

enum QueueError: Error{
    case NoSuchElementException
}
