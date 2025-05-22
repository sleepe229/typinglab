import axios from "axios";
import "../style.css";
import "../typingtest.css";
import { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";

function TypingTest() {
    const navigate = useNavigate();
    const [words, setWords] = useState([]);
    const [generatedText, setGeneratedText] = useState("");
    const [inputText, setInputText] = useState("");
    const [timeLeft, setTimeLeft] = useState(60);
    const [isRunning, setIsRunning] = useState(false);
    const [errorCount, setErrorCount] = useState(0);
    const [typedChars, setTypedChars] = useState(0);
    const [textOffset, setTextOffset] = useState(0);
    const intervalRef = useRef(null);
    const charWidth = 10;

    const difficulty = new URLSearchParams(window.location.search).get("level") || 1;

    const handleStart = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/api/typinglab/users/words?level=${difficulty}`);
            console.log("Raw API response.data:", response.data);
            let wordsArray = response.data;
            if (typeof wordsArray === "string") {
                wordsArray = wordsArray.split(",").map(word => String(word).trim()).filter(word => word.length > 0);
            } else if (Array.isArray(wordsArray)) {
                wordsArray = wordsArray.map(word => String(word).trim()).filter(word => word.length > 0);
            } else if (typeof wordsArray === "object") {
                wordsArray = Object.values(wordsArray)
                    .map(word => String(word).trim().replace(/,/g, ""))
                    .filter(word => word.length > 0);
            }
            console.log("Cleaned words array:", wordsArray);
            setWords(wordsArray);
            setGeneratedText(wordsArray.join(" "));
            setInputText("");
            setTimeLeft(60);
            setIsRunning(true);
            setErrorCount(0);
            setTypedChars(0);
            setTextOffset(0);
        } catch (error) {
            console.error("Ошибка при получении слов:", error);
        }
    };

    const handleEnd = async () => {
        setIsRunning(false);
        clearInterval(intervalRef.current);
        await sendStats();
    };

    useEffect(() => {
        if (isRunning && timeLeft > 0) {
            intervalRef.current = setInterval(() => {
                setTimeLeft(prev => prev - 1);
            }, 1000);
        } else if (timeLeft === 0 && isRunning) {
            setIsRunning(false);
            clearInterval(intervalRef.current);
            sendStats();
        }
        return () => clearInterval(intervalRef.current);
    }, [isRunning, timeLeft]);

    const sendStats = async () => {
        const accuracy = generatedText.length === 0 ? 0 : Math.max(0, 100 - Math.round((errorCount / generatedText.length) * 100));
        const speed = Math.round(typedChars / 60);
        const totalMissClick = errorCount;
        const userId = localStorage.getItem("userId");
        const totalCharactersTyped = typedChars;

        try {
            await axios.post('http://localhost:8080/api/typinglab/users/stats/update', {
                userId,
                totalMissClick,
                totalCharactersTyped
            });
            console.log("Статистика отправлена");
        } catch (err) {
            console.error("Ошибка при отправке статистики:", err);
        }
    };

    const handleInputChange = (e) => {
        const value = e.target.value;
        setInputText(value);
        setTypedChars(value.length);

        let errors = 0;
        for (let i = 0; i < value.length; i++) {
            if (value[i] !== generatedText[i]) {
                errors++;
            }
        }
        setErrorCount(errors);

        setTextOffset(value.length * charWidth);
    };

    const formatTime = (seconds) => {
        const mins = Math.floor(seconds / 60);
        const secs = seconds % 60;
        return `${mins.toString().padStart(2, "0")}:${secs.toString().padStart(2, "0")}`;
    };

    // const handleRedirect = () => {
    //     window.location.href = "lk.html";
    // };
    const renderText = () => {
        return generatedText.split("").map((char, index) => {
            let style = {};
            if (index < inputText.length) {
                style = {
                    color: inputText[index] === char ? "green" : "red",
                };
            }
            return (
                <span key={index} style={style}>
                    {char}
                </span>
            );
        });
    };

    return (
        <div className="gradbg">
            <nav className="desktop_nav">
                <div className="logo">
                    <a href="#"><img src="/assets/full%20logo.svg" alt="Logo" /></a>
                </div>
                <ul className="nav_links">
                    <li className="link"><a href="/">Home</a></li>
                    <li className="link"><a href="typing-test.html" className="active">Tests</a></li>
                    <li className="link"><a href="/statistics">Stats</a></li>
                </ul>
                <div className="buttonss">
                    <button id="btnsi" className="sign_in_btn" onClick={() => navigate("/profile")}>My Profile</button>
                </div>
            </nav>

            <div className="typing-test-container">
                <div className="timer-display">Time: {formatTime(timeLeft)}</div>
                <h1 className="typing-test-title">Typing Test</h1>

                <button
                    onClick={isRunning ? handleEnd : handleStart}
                    className="btn-custom"
                >
                    {isRunning ? "Закончить тренировку" : "Начать тренировку"}
                </button>

                <div className="text-container">
                    <div id="generated-text" className="generated-text" style={{ transform: `translateX(-${textOffset}px)` }}>
                        {renderText()}
                    </div>
                </div>

                <input
                    id="typing-input"
                    className="typing-input"
                    placeholder="Type here..."
                    value={inputText}
                    onChange={handleInputChange}
                    disabled={!isRunning}
                />

                <div id="result" className="result"></div>

                <div className="stats">
                    <p>Ошибки: <span id="error-count">{errorCount}</span></p>
                    <p>Введено символов: <span id="typed-count">{typedChars}</span></p>
                    <p>Оставшееся время: <span id="time-left">{timeLeft}</span> сек</p>
                    <p>Точность: <span id="accuracy">
                        {generatedText.length > 0
                            ? Math.max(0, 100 - Math.round((errorCount / typedChars) * 100))
                            : 0}
                        %
                    </span></p>
                </div>
            </div>
        </div>
    );
}

export default TypingTest;