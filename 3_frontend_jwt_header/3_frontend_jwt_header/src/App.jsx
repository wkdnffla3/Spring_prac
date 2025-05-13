import './App.css'
import React, { useEffect, useState } from 'react';

function App() {
  const [token, setToken] = useState(null);

  useEffect(() => {
    const savedToken = localStorage.getItem('jwt');
    if (savedToken) {
      setToken(savedToken); // 상태 복원
    }
  }, []);

  const login = async () => {
    const res = await fetch('http://localhost:8080/api/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username: 'user', password: 'pass' })
    });
    const text = await res.text();
    if (text !== 'fail') {
      setToken(text);
      localStorage.setItem('jwt', text);
    } else {
      alert('로그인 실패');
    }
  };

  const sendData = async () => {
    const res = await fetch('http://localhost:8080/api/data', {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('jwt')
      }
    });
    const text = await res.text();
    alert(text);
  };

  const logout = () => {
    localStorage.removeItem('jwt');
    setToken(null);
  };

  return (
    <div>
      {token ? <p>로그인됨</p> : <p>로그아웃 상태</p>}
      <button onClick={login}>로그인</button>
      <button onClick={sendData}>값 전송</button>
      <button onClick={logout}>로그아웃</button>
    </div>
  );
}

export default App;
