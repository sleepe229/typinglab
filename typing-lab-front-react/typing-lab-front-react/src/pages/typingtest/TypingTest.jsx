import axios from "axios";
import "../style.css";
import "../typingtest.css";
import { useState, useEffect, useRef } from "react";

function TypingTest() {
    const [words, setWords] = useState([]);
    const [timeLeft, setTimeLeft] = useState(60);
    const [isRunning, setIsRunning] = useState(false);
    const [userInput, setUserInput] = useState("");
    const [errors, setErrors] = useState(0);
    const [totalTyped, setTotalTyped] = useState(0);

    const intervalRef = useRef(null);
    const inputRef = useRef(null);

    const getDifficultyFromURL = () => {
        const params = new URLSearchParams(window.location.search);
        return params.get("difficulty") || "1";
    };

    const startTest = async () => {
        setIsRunning(false);
        setUserInput("");
        setErrors(0);
        setTotalTyped(0);
        setTimeLeft(60);

        try {
            const difficulty = getDifficultyFromURL();
            const response = await axios.get(`/api/typinglab/users/words?level=${difficulty}`);
            console.log(response.data);
            const joinedText = wordsArray.join(" ");
            setWords(wordsArray);
        } catch (error) {
            console.error("Error fetching words:", error);
        }
    };

    useEffect(() => {
        if (isRunning && timeLeft > 0) {
            intervalRef.current = setInterval(() => {
                setTimeLeft((prev) => prev - 1);
            }, 1000);
        }

        if (timeLeft === 0 && isRunning) {
            clearInterval(intervalRef.current);
            setIsRunning(false);
            sendResults();
        }

        return () => clearInterval(intervalRef.current);
    }, [isRunning, timeLeft]);

    const sendResults = async () => {
        const charsTyped = userInput.length;
        const speed = charsTyped / 60;
        const accuracy = charsTyped === 0 ? 0 : Math.round(((charsTyped - errors) / charsTyped) * 100);

        try {
            await axios.post("/api/typinglab/users/statistics", {
                speed,
                charactersTyped: charsTyped,
                errorPercentage: 100 - accuracy,
            });
        } catch (error) {
            console.error("Error sending results:", error);
        }
    };

    const handleInputChange = (e) => {
        const value = e.target.value;
        const originalText = words.join(" ").slice(0, value.length);

        let errorCount = 0;
        for (let i = 0; i < value.length; i++) {
            if (value[i] !== originalText[i]) {
                errorCount++;
            }
        }

        setUserInput(value);
        setTotalTyped(value.length);
        setErrors(errorCount);
    };

    return (
        <div className="gradbg">
            <nav className="desktop_nav">
                <div className="logo">
                    <a href="#"><img src="/assets/full%20logo.svg" alt="Logo" /></a>
                </div>
                <ul className="nav_links">
                    <li className="link"><a href="index.html">Home</a></li>
                    <li className="link"><a href="typing-test.html" className="active">Typing Test</a></li>
                    <li className="link"><a href="profile.html">Profile</a></li>
                </ul>
                <div className="buttonss">
                    <button id="btnsi" className="sign_in_btn" onClick={() => window.location.href = "lk.html"}>My Profile</button>
                </div>
            </nav>

            <div className="typing-test-container">
                <div className="timer-display">
                    Time Left: {timeLeft}s
                </div>

                <h1 className="typing-test-title">Typing Test</h1>

                <button className="btn-custom" onClick={startTest}>Start Test</button>

                <div id="generated-text" className="generated-text">
                    {words.join(" ")}
                </div>
                <input
                    id="typing-input"
                    ref={inputRef}
                    className="typing-input"
                    value={userInput}
                    onChange={handleInputChange}
                    disabled={!isRunning}
                    placeholder="Start typing here..."
                />

                <div className="stats">
                    <p>Errors: <span>{errors}</span></p>
                    <p>Typed: <span>{totalTyped}</span></p>
                    <p>Accuracy: <span>{totalTyped ? Math.round(((totalTyped - errors) / totalTyped) * 100) : 0}</span>%</p>
                </div>
            </div>
        </div>
    );
}

export default TypingTest;