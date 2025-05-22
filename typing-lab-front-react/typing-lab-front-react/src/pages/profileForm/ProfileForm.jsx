import { useState } from 'react';
import axios from 'axios';
import "./main.css";
import { useNavigate } from 'react-router-dom';

export default function ProfileForm() {
    const navigate = useNavigate();
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    username: '',
    email: '',
    birthdate: '',
    country: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const userId = localStorage.getItem('userId');
    const payload = { userId, ...formData };

    try {
    //   await axios.post('/api/typinglab/users/profile', payload);
      alert('Профиль обновлен!');
    } catch (error) {
      console.error(error);
      alert('Ошибка при обновлении профиля');
    }
  };

  return (
        <div className="container">
        <aside className="sidebar">
            <section className="section-side">
                <div style={{width: '90%', height: '90%'}}>
                    <img className="imageBase__img" src="assets/full logo.svg" />
                </div>

                <div className="profile">
                    <div className="profile-pic">
                        <div
                                className="photo"
                                data-testid="profile-avatar"
                                style={{width: '90%', height: '90%'}}
                        >
                            <img className="imageBase__img" src="assets/v1_8.png" />
                            <div aria-hidden="true" className="imageBase__border"></div>
                        </div>
                    </div>

                    <h2 className="name">Nikita Novikov</h2>
                    <span className="username">@Ezhyyy</span>
                    <button className="btn edit-btn">Edit</button>
                </div>
                <nav className="menu nav_lk">
                    <div className="menu-item" onClick={() => navigate("/")}>Home</div>
                    <div className="menu-item">Stats</div>
                    <div className="menu-item">Test</div>
                    <div className="menu-item">Training</div>
                </nav>
            </section>

            <div className="upgrade-box">
                <h3>Upgrade to Pro</h3>
                <p>Get 1 month free and unlock all features</p>
                <button className="btn upgrade-btn">Upgrade</button>
            </div>
            <section className="section-items">
                <div className="settings">
                    <div className="menu-item">Settings</div>
                    <div className="menu-item">Log out</div>
                </div>
            </section>
        </aside>

        <main className="main-content">
            <h1 className="main-title">Edit User Profile</h1>
            <hr className="title-line" />

            <div className="content-container">

                <div className="left-column">
                    <section className="photo-parent">
                        <section className="back">
                            <div
                                    className="profileCover profileCover--red profileCover--img"
                                    style={{backgroundImage: 'url(assets/v1_67.png)'}}
                            >
                                <div className="profileCoverShadowWrapper">
                                    <div
                                            className="profileCover profileCover--red profileCover--img"
                                            style={{backgroundImage: 'url(assets/v1_67.png)'}}
                                    ></div>
                                </div>
                            </div>
                        </section>

                        <section className="profile-photo">
                            <div className="photo-wrapper">
                                <div
                                        className="photo"
                                        data-testid="profile-avatar"
                                        style={{width: '90%', height: '90%'}}
                                >
                                    <img className="imageBase__img" src="assets/cool.jpg" />
                                    <div aria-hidden="true" className="imageBase__border"></div>
                                </div>
                            </div>

                            <p className="photo-info">This will be displayed on your profile</p>
                            <div className="photo-actions">
                                <button className="btn upload-btn">Upload new</button>
                                <button className="btn save-btn">Save</button>
                            </div>
                        </section>
                    </section>

                    <section className="security-section">
                        <h2>Security</h2>
                        <form>
                            <div>
                                <label htmlFor="current-password">Current Password</label>
                                <input
                                        type="password"
                                        id="current-password"
                                        value="uvirftyrft7ib"
                                />
                            </div>
                            <div>
                                <label htmlFor="new-password">New Password</label>
                                <input type="password" id="new-password" value="uifdnhfid" />
                            </div>
                            <div>
                                <label htmlFor="repeat-password">Repeat Password</label>
                                <input
                                        type="password"
                                        id="repeat-password"
                                />
                            </div>
                            <button className="btn confirm-btn">Confirm</button>
                        </form>
                    </section>
                </div>

                <div className="right-column">
                    <section className="personal-info">
                        <h2>Personal Information</h2>
                        <form onSubmit={handleSubmit}>
                            <div>
                                <label for="first-name">First Name</label>
                                <input
                                    type="text"
                                    name="firstName"
                                    placeholder="First Name"
                                    value={formData.firstName}
                                    onChange={handleChange}
                                />
                            </div>

                            <div>
                                <label for="last-name">Last Name</label>
                                <input
                                    type="text"
                                    name="lastName"
                                    placeholder="Last Name"
                                    value={formData.lastName}
                                    onChange={handleChange}
                                />
                            </div>
                            
                            <div>
                                <label for="username">Username</label>
                                <input
                                    type="text"
                                    name="username"
                                    placeholder="Username"
                                    value={formData.username}
                                    onChange={handleChange}
                                />
                            </div>

                            <div>
                                <label for="email">Email</label>
                                <input
                                    type="email"
                                    name="email"
                                    placeholder="Email"
                                    value={formData.email}
                                    onChange={handleChange}
                                />
                            </div>

                            <div>
                                <label for="birth-date">Birth Date</label>
                                <input
                                    type="date"
                                    name="birthdate"
                                    placeholder="Birthdate"
                                    value={formData.birthdate}
                                    onChange={handleChange}
                                />
                            </div>
                            
                            <div>
                                <label for="country">Country</label>
                                <input
                                    type="text"
                                    name="country"
                                    placeholder="Country"
                                    value={formData.country}
                                    onChange={handleChange}
                                />
                            </div>
                            
                            <button className="btn confirm-btn">Confirm</button>
                        </form>
                    </section>

                    <section className="delete-account">
                        <h2>Delete Account</h2>
                        <div className="delete-content">
                            <p>
                                If you delete the account, you will not be able to get it
                                back.
                            </p>
                            <button className="btn delete-btn">Delete</button>
                        </div>
                    </section>
                </div>
            </div>
        </main>
    </div>
  );
};