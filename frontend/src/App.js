import { useState } from "react";
import axios from "axios";

function App() {
    const [errorText, setErrorText] = useState("");
    const [result, setResult] = useState(null);

    const analyzeError = async () => {
        try {
            const response = await axios.post("http://localhost:8080/api/analyze", errorText);
            setResult(response.data);
        } catch (err) {
            console.error(err);
        }
    };

    return (
        <div className="container mt-5">
            <h1>API Error Translator</h1>

            <textarea
                className="form-control mt-3"
                rows="5"
                placeholder="Paste your error here..."
                value={errorText}
                onChange={(e) => setErrorText(e.target.value)}
            />

            <button className="btn btn-primary mt-3" onClick={analyzeError}>
                Analyze
            </button>

            {result && (
                <div className="mt-4">
                    <h4>Explanation</h4>
                    <ul>
                        {result.explanations.map((exp, i) => (
                            <li key={i}>{exp}</li>
                        ))}
                    </ul>

                    <h4>Possible Fixes</h4>
                    <ul>
                        {result.possibleFixes.map((fix, i) => (
                            <li key={i}>{fix}</li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
}

export default App;