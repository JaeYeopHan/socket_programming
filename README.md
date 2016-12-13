## Socket_programming in JAVA

> 이 프로젝트는 멀티채팅방을 JAVA의 Socket 라이브러리를 사용하여 구현한 프로젝트입니다. GUI는 없으며 console을 통해서 채팅을 할 수 있으며, 여러 클라이언트가 동시에 채팅을 할 수 있습니다.  Localhost(루프 백 주소)에 binding을 해두어 다른 컴퓨터에서 접근은 불가능합니다.

### Compile/Installation guide 

#### Installation
```
mkdir multichat
cd multichat
git clone https://github.com/JaeYeopHan/socket_programming.git
```

#### Compile
```
javac Server.java
javac Client.java
```
---

### User guide
* First, running the Chatting server.
```
java Server
```
* Second, running the Chatting Client with ID
```
java Client jbee
```
* Just chatting with myself :)

---

### Content
#### Socket 라이브러리
* socket()
소켓 생성, 소켓 디스크립터 정보 반환하는 메소드 
* bind()
소켓의 주소 정보를 할당하는 메소드 
* accept()
연결 요청 대기 큐에 있는 것을 하나씩 추출하여 connect해주는 역할 
* connect() 
클라이언트에서 서버로 요청을 보내는 메소드 
