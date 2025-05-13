import { useEffect, useState } from 'react';
import './App.css';

// jsx 확장자가 더 react 스럽다.


function App() {
  const [username, setUsername] = useState(null);

  // 컴포넌트가 생성될때 /session-user api 호출해서 로그인 여부 확인
  // 또는 cookie 나 session/localstroage 에 저장햇다가 불러오기.
  // 새로고침하면 실행되는 코드
  useEffect(() => {
    const fetchSessionUser = async () => {
      const res = await fetch('http://localhost:8080/api/session-user', {
        method: 'GET',
        credentials: 'include'
      });
      const name = await res.text();
      if (name) {
        setUsername(name);
      }
    };

    fetchSessionUser();
  }, []);

  const login = async () => {
    const res = await fetch('http://localhost:8080/api/login', {
      method: 'POST',
      credentials: 'include',
      // 얘가 없으면 session 처리를 못한다 쿠키가 있어도 작동을 하지 않음
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username: 'user', password: 'pass' })
      // json 형태로 전달중 백에서는 requestbody로 받고 있어서 json 이다.
    });

    if (res.ok) {
      setUsername('user');
      // 반응이 ok 이면 user state에 user를 저장한다.
    } else {
      alert('로그인 실패');
    }
  };

  const sendData = async () => {
    const res = await fetch('http://localhost:8080/api/data', {
      method: 'GET',
      credentials: 'include'
      // credentials 를 include 속성으로 넣어줘야만 backend 에서 발행한 쿠키를 담을수 있다.
    });
    const text = await res.text();
    alert(text);
  };

  const logout = async () => {
    await fetch('http://localhost:8080/api/logout', {
      method: 'POST',
      credentials: 'include'
    });
    setUsername(null);
  };

  return (
    <div>
      {username ? <p>로그인된 사용자: {username}</p> : <p>로그인되지 않음</p>}
      <button onClick={login}>로그인</button>
      <button onClick={sendData}>값 전송</button>
      <button onClick={logout}>로그아웃</button>
    </div>
  );
}

// 여기부터 위로 올라가면서 본다.
export default App;
