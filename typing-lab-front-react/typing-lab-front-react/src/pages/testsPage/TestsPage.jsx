import React from "react";
import { useNavigate } from "react-router-dom";
import styles from "./tests.module.css";
const TestsPage = () => {
  const navigate = useNavigate();

  const handleButtonClick = (level, name) => {
    navigate("/tipingtest", { state: { level, name } });
  };

  return (
    <div className={styles.testsPage}>
      <div className={styles.desktopNav}>
        <div className={styles.logo}>
          <img src="/assets/full%20logo.svg" alt="TypingLab Logo" />
        </div>
        <ul className={styles.navLinks}>
          <li className={styles.link}>
            <a href="/tests">Tests</a>
          </li>
          <li className={styles.link}>
            <a href="/stats">Stats</a>
          </li>
          <li className={styles.link}>
            <a href="/profile">Profile</a>
          </li>
        </ul>
        <div className={styles.buttonss}>
          <button
            className={styles.signInBtn}
            onClick={() => navigate("/logout")}
          >
            Logout
          </button>
        </div>
      </div>
      <div className={styles.testsContainer}>
        <h1 className={styles.testsTitle}>Choose Your Test Level</h1>
        <div className={styles.testsGrid}>
          <button
            className={styles.testCard}
            onClick={() => handleButtonClick("1", "Begginer")}
          >
            <h3>For Beginners</h3>
            <p className={styles.testDescription}>
              Start your typing journey with easy words and simple challenges.
            </p>
          </button>
          <button
            className={styles.testCard}
            onClick={() => handleButtonClick("2", "Intermedia")}
          >
            <h3>For Intermediate Level</h3>
            <p className={styles.testDescription}>
              Challenge yourself with moderately complex words and faster
              pacing.
            </p>
          </button>
          <button
            className={styles.testCard}
            onClick={() => handleButtonClick("3", "Advanced")}
          >
            <h3>For Advanced</h3>
            <p className={styles.testDescription}>
              Test your skills with difficult words and higher accuracy demands.
            </p>
          </button>
          <button
            className={`${styles.testCard} ${styles.lastButton}`}
            onClick={() => handleButtonClick("4", "PRO")}
          >
            <h3>For PRO</h3>
            <p className={styles.testDescription}>
              Master the ultimate typing challenge with expert-level tasks.
            </p>
          </button>
        </div>
      </div>
    </div>
  );
};

export default TestsPage;
