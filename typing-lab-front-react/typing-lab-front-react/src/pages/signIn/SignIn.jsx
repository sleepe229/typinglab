import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import { useState } from "react";
import "./sign_in_cs.css";

export default function SignIn() {
  const [login, setLogin] = useState("");
  const [password, setPassword] = useState("");
  const [rememberMe, setRememberMe] = useState(false);
  const navigate = useNavigate();

  const handleSignIn = async () => {
    try {
      const response = await axios.post("http://localhost:8080/api/typinglab/users/login", {
        login,
        password,
      });

      const user = response.data;
      console.log("Logged in:", user);

      if (rememberMe) {
        localStorage.setItem("user", JSON.stringify(user));
      }

      navigate("/");
    } catch (error) {
      console.error("Login failed:", error);
      alert("Login failed. Please check your credentials.");
    }
  };

  return (
    <div className="bg">
      <div className="form">
        <div className="logo">
          <img src="/assets/Group%2015.svg" alt="Logo" />
        </div>
        <div className="subtitle">
          <h1>Log in with username/email</h1>
        </div>
        <div className="input-container ic1">
          <input
            className="input"
            type="text"
            placeholder=" "
            value={login}
            onChange={(e) => setLogin(e.target.value)}
          />
          <div className="cut" />
          <label className="placeholder">Login</label>
        </div>
        <div className="input-container ic2">
          <input
            className="input"
            type="password"
            placeholder=" "
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <div className="cut" />
          <label className="placeholder">Password</label>
        </div>
        <div className="remember_forgot">
          <label>
            <input
              type="checkbox"
              className="checkboxrem"
              checked={rememberMe}
              onChange={(e) => setRememberMe(e.target.checked)}
            />
            Remember me
          </label>
          <a href="#">Recover password</a>
        </div>
        <button type="button" className="submit" onClick={handleSignIn}>
          Sign in
        </button>
        <div className="create_acc">
          <Link to="/sign-up">Create account</Link>
        </div>
      </div>
    </div>
  );
}