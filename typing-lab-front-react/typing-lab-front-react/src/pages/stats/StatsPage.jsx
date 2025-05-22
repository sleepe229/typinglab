import { useState, useEffect } from 'react';
import './stats.css';
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
                const response = await axios.post(`http://localhost:8080/api/typinglab/users/stats`, { userId })
                setStats({
                    ...response.data,
                    isLoading: false,
                    error: null
                });
            
                // const response = await fetch('http://localhost:8080/api/typinglab/users/stats');
                // if (!response.ok) {
                //     throw new Error('Failed to fetch stats');
                // }
                // const data = await response.json();
                // setStats({
                //     ...data,
                //     isLoading: false,
                //     error: null
                // });
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
        <div className="stat-card">
            <h3>{title}</h3>
            <div className="stat-value">
                {value} {unit && <span>{unit}</span>}
            </div>
            {description && <p className="stat-description">{description}</p>}
        </div>
    );

    if (stats.isLoading) {
        return (
            <div className="stats-loading">
                <div className="spinner"></div>
                <p>Loading statistics...</p>
            </div>
        );
    }

    // if (stats.error) {
    //     return (
    //         <div className="stats-error">
    //             <p>Error loading statistics: {stats.error}</p>
    //             <button onClick={() => window.location.reload()}>Retry</button>
    //         </div>
    //     );
    // }

    return (
        <div className="stats-page gradbg">
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
                        <a href="stats.html" className="active">Statistics</a>
                    </li>
                    <li className="link">
                        <a href="profile.html">Profile</a>
                    </li>
                </ul>
                <div className="buttonss">
                    <a href="profile.html">
                        <button className="sign_in_btn">My Profile</button>
                    </a>
                </div>
            </nav>

            <div className="stats-container">
                <h1 className="stats-title">Your Typing Statistics</h1>

                <div className="stats-grid">
                    {renderStatCard(
                        'Average Speed',
                        stats.averageTypingSpeed,
                        'CPM',
                        'Characters per minute'
                    )}
                    {renderStatCard(
                        'Completed Trainings',
                        stats.completedTrainings,
                        '',
                        'Total sessions'
                    )}
                    {renderStatCard(
                        'Best Speed',
                        stats.maxTypingSpeed,
                        'CPM',
                        'Personal record'
                    )}
                    {renderStatCard(
                        'Miss Clicks',
                        stats.missClickPercentage,
                        '%',
                        'Error rate'
                    )}
                </div>
            </div>
        </div>
    );
}

export default StatsPage;