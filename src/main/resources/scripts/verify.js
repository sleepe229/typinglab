
const handleInput = (inputNum) => {
    const currentInput = document.querySelector(`.input-container input:nth-child(${inputNum})`);
    const nextInput = document.querySelector(`.input-container input:nth-child(${inputNum + 1})`);
    if(currentInput.value.length === 1 && nextInput){
        nextInput.disabled = false;
        nextInput.focus();
    }
    const allInputsFilled = Array.from(document.querySelectorAll('.input-container input')).every(input => input.value.length === 1);
    const submitbtn = document.querySelector('.submitBTN');
    submitbtn.disabled = !allInputsFilled;
}
const handleBackspace = (inputNum, event) => {
    if (event.key === "Backspace") {
        const currentInput = document.querySelector(`.input-container input:nth-child(${inputNum})`);
        const prevInput = document.querySelector(`.input-container input:nth-child(${inputNum - 1})`);
        if (currentInput.value.length === 0 && prevInput){
            currentInput.disabled = true;
            currentInput.value = "";
            prevInput.focus();
        }
        const allInputsFilled = Array.from(document.querySelectorAll('.input-container input')).every(input=> input.value.length === 1);
        const submitbtn = document.querySelector('.submitBTN')
        submitbtn.disabled = !allInputsFilled;
    }
}

