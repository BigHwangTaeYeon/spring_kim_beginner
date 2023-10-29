HashMap과 ConcurrentHashMap의 차이점
1) Thread Safe
주요 차이점은 ConcurrentHashMap는 내부적 동기화 때문에 스레드가 Safe합니다.
HashMap는 내부적으로 동기화되지 않고 스레드로부터 안전하지 않습니다. HashMap 메서드를 사용하여 외부에서 동기화 할 수 있습니다.

2) Internal Structure(내부구조)
ConcurrentHashMap의 모든 작업이 동기화되는 것은 아닙니다. 추가 및 삭제와 같은 수정 작업만 동기화됩니다. 읽기 작업은 동기화되지 않습니다. 이렇게 하면 ConcurrentHashMap이 외부에서 동기화된 HashMap보다 동시 다중 스레드 응용 프로그램에 대한 첫 번째 선택 맵이 됩니다.

4) Null Keys And Null Values
HashMap은 최대 하나의 null 키와 임의의 수의 null 값을 허용합니다.
ConcurrentHashMap은 null 키와 null 값도 허용하지 않습니다.

5) Fail-Fast Vs Fail-Safe
HashMap에 의해 반환된 반복자는 본질적으로 빠른 속도입니다. 반복자 생성 후 맵이 수정되면 ConcurrentModificationException이 발생하기 때문입니다.
ConcurrentHashMap에 의해 반환된 반복자는 본질적으로 안전합니다. iterator 생성 후 맵이 수정되면 예외가 발생하지 않습니다.

6) Performance(성능)
ConcurrentHashMap에 대한 수정 작업만 동기화됩니다. 따라서 ConcurrentHashMap에 대한 추가 또는 제거 작업은 HashMap보다 느립니다. ConcurrentHashMap 및 HashMap 모두에 대한 읽기 작업은 두 맵의 읽기 작업이 동일한 성능을 제공합니다.

 
결론적으로 ConcurrentHashMap는 내부적으로 동기화 함으로, 동시 멀티 쓰레드 어플리케이션에 적합합니다. HashMap은  내부적으로 동기화 되지않습니다. 따라서  단일 쓰레드 프로그램에 적합합니다.