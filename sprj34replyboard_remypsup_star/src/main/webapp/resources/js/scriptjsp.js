 const drawStar = (target) => {
  console.log("sign>>>>"+target.value);
    document.querySelector(`.star span`).style.width = `${target.value * 10}%`;
   
  }