import './App.css'

import React, { useState, useEffect } from 'react';

function App() {
  const [loggedIn, setLoggedIn] = useState(false);

  useEffect(() => {
    // 새로고침 시 자동 로그인 확인
    checkLogin();
  }, []);

  const checkLogin = async () => {
    const res = await fetch('http://localhost:8080/api/data', {
      method: 'GET',
      credentials: 'include'
    });
    if (res.status === 200) {
      setLoggedIn(true);
    } else {
      setLoggedIn(false);
    }
  };

  const login = async () => {
    const res = await fetch('http://localhost:8080/api/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      credentials: 'include',
      body: JSON.stringify({ username: 'user', password: 'pass' })
    });
    const text = await res.text();
    if (text === '로그인 성공') {
      setLoggedIn(true);
    }
  };

  const sendData = async () => {
    const res = await fetch('http://localhost:8080/api/data', {
      method: 'GET',
      credentials: 'include'
    });
    const text = await res.text();
    alert(text);
  };

  const logout = async () => {
    await fetch('http://localhost:8080/api/logout', {
      method: 'POST',
      credentials: 'include'
    });
    setLoggedIn(false);
  };

  return (
    <div>
      {loggedIn ? <p>로그인됨</p> : <p>로그아웃 상태</p>}
      <button onClick={login}>로그인</button>
      <button onClick={sendData}>값 전송</button>
      <button onClick={logout}>로그아웃</button>
    </div>
  );
}

export default App;
