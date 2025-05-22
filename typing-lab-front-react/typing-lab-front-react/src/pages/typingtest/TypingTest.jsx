import "../style.css";
import "../typingtest.css";
import { useState, useEffect, useRef } from "react";

function TypingTest() {
    const [time, setTime] = useState(0);
    const [isActive, setIsActive] = useState(false);
    const timerRef = useRef(null);
    const startTimeRef = useRef(null);

    const handleRedirect = () => {
        window.location.href = "lk.html";
    };

    useEffect(() => {
        if (isActive) {
            timerRef.current = setInterval(() => {
                setTime((prevTime) => prevTime + 1);
            }, 1000);
        } else {
            clearInterval(timerRef.current);
        }

        return () => clearInterval(timerRef.current);
    }, [isActive]);

    const handleFormSubmit = async (event) => {
        event.preventDefault();

        const basePrompt = document.getElementById('basePrompt').value;
        const difficulty = document.getElementById('difficulty').value;
        const generatedTextDiv = document.getElementById('generated-text');
        const resultDiv = document.getElementById('result');
        const errorCountSpan = document.getElementById('error-count');
        const timeTakenSpan = document.getElementById('time-taken');
        const accuracySpan = document.getElementById('accuracy');
        const typingInput = document.getElementById('typing-input');

        try {
            const response = await fetch(`http://localhost:8080/textGeneration/gen?basePrompt=${encodeURIComponent(basePrompt)}&difficulty=${encodeURIComponent(difficulty)}`);
            if (!response.ok) {
                throw new Error(`Error: ${response.statusText}`);
            }
            const text = await response.text();
            generatedTextDiv.textContent = text;
            resultDiv.textContent = '';
            typingInput.value = '';
            errorCountSpan.textContent = '0';
            timeTakenSpan.textContent = '0';
            accuracySpan.textContent = '0';
            setTime(0);
            setIsActive(false);
        } catch (error) {
            generatedTextDiv.textContent = `Failed to fetch text: ${error.message}`;
        }
    };

    const handleKeyDown = (event) => {
        const generatedText = document.getElementById('generated-text').textContent;
        if (!generatedText) return;

        if (!isActive) {
            setIsActive(true);
            startTimeRef.current = Date.now();
        }

        const currentIndex = event.target.value.length;
        const pressedKey = event.key;

        if (pressedKey === 'Shift' || pressedKey === 'Control' || pressedKey === 'Alt') {
            return;
        }

        if (pressedKey !== generatedText[currentIndex] && pressedKey !== 'Backspace') {
            event.preventDefault();
        }
    };

    const formatTime = (seconds) => {
        const mins = Math.floor(seconds / 60);
        const secs = seconds % 60;
        return `${mins.toString().padStart(2, '0')}:${secs.toString().padStart(2, '0')}`;
    };

    return (
        <div className="gradbg">
            <nav className="desktop_nav">
                <div className="logo">
                    <a href="#">
                        <img src="/assets/full%20logo.svg" alt="Logo" />
                    </a>
                </div>
                <ul className="nav_links">
                    <li className="link">
                        <a href="index.html">Home</a>
                    </li>
                    <li className="link">
                        <a href="typing-test.html" className="active">Typing Test</a>
                    </li>
                    <li className="link">
                        <a href="profile.html">Profile</a>
                    </li>
                </ul>
                <div className="buttonss">
                    <button id="btnsi" className="sign_in_btn" onClick={handleRedirect}>My Profile</button>
                </div>
            </nav>

            <div className="typing-test-container">
                <div className="timer-display">
                    Time: {formatTime(time)}
                </div>

                <h1 className="typing-test-title">Typing Test</h1>

                <form id="text-generation-form" onSubmit={handleFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="basePrompt" className="form-label">Base Prompt:</label>
                        <input
                            type="text"
                            id="basePrompt"
                            className="form-control"
                            placeholder="Enter base prompt..."
                            required
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="difficulty" className="form-label">Difficulty (1-5):</label>
                        <input
                            type="number"
                            id="difficulty"
                            className="form-control"
                            min="1"
                            max="5"
                            placeholder="Enter difficulty..."
                            required
                        />
                    </div>

                    <button type="submit" className="btn-custom">Generate Text</button>
                </form>

                <div id="generated-text" className="generated-text">
                    Generated text will appear here...
                </div>

                <input
                    id="typing-input"
                    className="typing-input"
                    placeholder="Type here..."
                    onKeyDown={handleKeyDown}
                />

                <div id="result" className="result"></div>

                <div className="stats">
                    <p>Errors: <span id="error-count">0</span></p>
                    <p>Time: <span id="time-taken">0</span> seconds</p>
                    <p>Accuracy: <span id="accuracy">0</span>%</p>
                </div>
            </div>
        </div>
    );
}

export default TypingTest;