import { useState, useEffect } from 'react';
import styles from './stats.module.css';
import "../style.css";
import axios from 'axios';

function StatsPage() {
    const [stats, setStats] = useState({
        averageTypingSpeed: 0,
        completedTrainings: 0,
        maxTypingSpeed: 0,
        missClickPercentage: 0,
        isLoading: true,
        error: null
    });

    useEffect(() => {
        const fetchStats = async () => {
            try {
                const userId = localStorage.getItem("userId");
                const response = await axios.post(`http://localhost:8080/api/typinglab/users/stats`, { userId });
                setStats({
                    ...response.data,
                    isLoading: false,
                    error: null
                });
            } catch (error) {
                setStats(prev => ({
                    ...prev,
                    isLoading: false,
                    error: error.message
                }));
            }
        };
        fetchStats();
    }, []);

    const renderStatCard = (title, value, unit = '', description = '') => (
        <div className={styles['stat-card']}>
            <h3>{title}</h3>
            <div className={styles['stat-value']}>
                {value} {unit && <span>{unit}</span>}
            </div>
            {description && <p className={styles['stat-description']}>{description}</p>}
        </div>
    );

    if (stats.isLoading) {
        return (
            <div className={styles['stats-loading']}>
                <div className={styles.spinner}></div>
                <p>Loading statistics...</p>
            </div>
        );
    }

    return (
        <div className={styles['stats-page']}>
            <nav className={styles['desktop_nav']}>
                <div className={styles.logo}>
                    <a href="#">
                        <img src="/assets/full%20logo.svg" alt="Logo" />
                    </a>
                </div>
                <ul className={styles['nav_links']}>
                    <li className={styles.link}>
                        <a href="/">Home</a>
                    </li>
                    <li className={styles.link}>
                        <a href="/statistics" className={styles.active}>Statistics</a>
                    </li>
                    <li className={styles.link}>
                        <a href="/profile">Profile</a>
                    </li>
                </ul>
                <div className={styles.buttonss}>
                    <a href="/profile">
                        <button className={styles['sign_in_btn']}>My Profile</button>
                    </a>
                </div>
            </nav>

            <div className={styles['stats-container']}>
                <h1 className={styles['stats-title']}>Your Typing Statistics</h1>
                <div className={styles['stats-grid']}>
                    {renderStatCard('Average Speed', stats.averageTypingSpeed, 'CPM', 'Characters per minute')}
                    {renderStatCard('Completed Trainings', stats.completedTrainings, '', 'Total sessions')}
                    {renderStatCard('Best Speed', stats.maxTypingSpeed, 'CPM', 'Personal record')}
                    {renderStatCard('Miss Clicks', stats.missClickPercentage, '%', 'Error rate')}
                </div>
            </div>
        </div>
    );
}

export default StatsPage;