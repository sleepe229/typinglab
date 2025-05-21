import { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Landing from './pages/landing/Landing';
import TypingTest from "./pages/typingtest/TypingTest.jsx";

function App() {

  return (
    <Router>
      <Routes>
        <Route path='/' element={<Landing />} />
        <Route path='/tipingtest' element={<TypingTest />} />
      </Routes>
    </Router>
  )
}

export default App
