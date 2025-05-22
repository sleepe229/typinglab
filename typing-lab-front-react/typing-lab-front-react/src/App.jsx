import { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import SignIn from './pages/signIn/SignIn';
import Landing from './pages/landing/Landing';
import SignUp from './pages/signUp/SignUp';
import TypingTest from "./pages/typingtest/TypingTest.jsx";
import ProfileForm from './pages/profileForm/ProfileForm.jsx';

function App() {

  return (
    <Router>
      <Routes>
        <Route path='/' element={<Landing />} />
        <Route path='/sign-in' element={<SignIn />} />
        <Route path='/sign-up' element={<SignUp />} />
        <Route path='/tipingtest' element={<TypingTest />} />
        <Route path='/profile' element={<ProfileForm />} />
      </Routes>
    </Router>
  )
}

export default App
