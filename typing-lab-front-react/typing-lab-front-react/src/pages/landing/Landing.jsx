import "../style.css";

function scrollToContainer(selector, instance = 0) {
  const elements = document.querySelectorAll(selector);
  if (elements.length > instance) {
    elements[instance].scrollIntoView({ behavior: "smooth" });
  }
}

export default function Landing() {
  const handleRedirect = () => {
    window.location.href = "sign_up_page.html";
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
          <li id="link1" className="link" onClick={() => scrollToContainer(".block_1")}>
            <a href="#Product">Product</a>
          </li>
          <li id="link2" className="link" onClick={() => scrollToContainer(".block_2")}>
            <a href="#Subscription">Subscription</a>
          </li>
          <li id="link3" className="link" onClick={() => scrollToContainer(".block_3")}>
            <a href="#AboutUs">About us</a>
          </li>
        </ul>
        <div className="buttonss">
          <a href="sign_in_page.html">
            <button id="btnsi" className="sign_in_btn">Sign in</button>
          </a>
          <a href="sign_up_page.html">
            <button id="btnsu" className="sign_up_btn">Sign up</button>
          </a>
        </div>
      </nav>

      <header className="slogan">
        <div className="slogan_container">
          <h1 className="unlock">Unlock your</h1>
          <h1 className="typing">Typing <span>speed</span></h1>
        </div>
        <button id="btngs" className="get_started" onClick={handleRedirect}>Get Started</button>
        <div className="pepegaImg">
          <img src="/assets/2024-04-21%2002.52.23.jpg" alt="Typing Hero" />
        </div>
      </header>

      <div className="block_1">
        <div className="product">
          <h1 className="product_slogan">
            Quite simple <br /> & Too effective
          </h1>
          <p>
            You can extremely quickly develop your typing <br /> skills and most importantly, do it with pleasure. <br />
            The whole process is like a game.
          </p>
        </div>
        <div className="statss">
          <img src="/assets/Stats%201st%20section.png" alt="Stats" />
        </div>
      </div>

      <div className="block_2_bg">
        <section>
          <div className="block_2">
            <div className="subss">
              <img src="/assets/Basic%20subscrip.svg" alt="Basic Subscription" />
              <img src="/assets/Premium%20subscrip.svg" alt="Premium Subscription" />
            </div>
            <div className="subscrip">
              <h1 className="sub_slogan">
                Speed up <br /> your progress
              </h1>
              <p>
                Subscribe to Typing Lab Premium to get all <br />
                the features of our service and increase your <br />
                speed even faster
              </p>
            </div>
          </div>
        </section>
      </div>

      <div className="block_3">
        <div className="about_uss">
          <h1 className="auss">What goal are we pursuing?</h1>
          <p>
            Time is a very valuable thing, and we are just people who want to save it and <br />
            give you the opportunity to work more efficiently and, in addition, get a cool <br />
            hard skill in your piggy bank.
          </p>
        </div>
        <img src="/assets/Stats.png" alt="More Stats" />
      </div>

      <div className="foot_bg">
        <footer className="containerrr">
          <div className="column">
            <div className="logo_foot">
              <img src="/assets/full%20logo%20bottom.svg" alt="Logo Bottom" />
            </div>
          </div>
          <div className="column">
            <h4>Company</h4>
            <a href="#">Jobs</a>
            <a href="#">About</a>
          </div>
          <div className="column">
            <h4>Resources</h4>
            <a href="#">Support</a>
            <a href="#">Blog</a>
            <a href="#">Developers</a>
            <a href="#">Community</a>
          </div>
          <div className="column">
            <h4>Policies</h4>
            <a href="#">Terms</a>
            <a href="#">Privacy</a>
          </div>
        </footer>
      </div>
    </div>
  );
}
