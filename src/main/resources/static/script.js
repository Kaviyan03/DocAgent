function uploadPdf() {
	const file = document.getElementById("pdfFile").files[0];
	if (!file) {
		document.getElementById("uploadStatus").textContent = "Please select a PDF file.";
		return;
	}
	document.getElementById("uploadStatus").textContent = "Uploading document...";

	const formData = new FormData();
	formData.append("file", file);

	fetch("/upload", { method: "POST", body: formData })
		.then(async response => {
			const data = await response.json().catch(() => ({}));
			if (!response.ok) {
				throw new Error(data.message || "The PDF could not be uploaded.");
			}
			return data;
		})
		.then(data => document.getElementById("uploadStatus").textContent = data.message)
		.catch(error => document.getElementById("uploadStatus").textContent = error.message);
}

function removePdf() {
	fetch("/upload", { method: "DELETE" })
		.then(response => response.json())
		.then(data => {
			document.getElementById("pdfFile").value = "";
			document.getElementById("uploadStatus").textContent = data.message;
			document.getElementById("response").textContent = "";
		document.getElementById("emptyState").style.display = "block";
		});
}

function sendMessage() {
	const message = document.getElementById("message").value;
	if (!message.trim()) {
		document.getElementById("response").textContent = "Please enter a question.";
		document.getElementById("emptyState").style.display = "none";
		return;
	}

	document.getElementById("emptyState").style.display = "none";
	document.getElementById("response").textContent = "Thinking...";

	fetch("/chat?message=" + encodeURIComponent(message))
		.then(response => response.json())
		.then(data => document.getElementById("response").textContent = data.reply)
		.catch(() => document.getElementById("response").textContent = "Unable to get a response. Please try again.");
}

function handleQuestionKey(event) {
	if (event.key === "Enter") {
		sendMessage();
	}
}