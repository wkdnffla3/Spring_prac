import { useEffect, useState } from 'react';
import './App.css';

function App() {
  const [username, setUsername] = useState(null);

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
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username: 'user', password: 'pass' })
    });

    if (res.ok) {
      setUsername('user');
    } else {
      alert('로그인 실패');
    }
  };

  const sendData = async () => {
    const res = await fetch('http://localhost:8080/api/data', {
      method: 'GET',
      credentials: 'include'
    });
    if(res.status == 403) {
      alert('로그인 안 됨');
    } else {
      const text = await res.text();
      alert(text);
    }
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

export default App;
