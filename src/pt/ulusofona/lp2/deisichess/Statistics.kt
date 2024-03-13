package pt.ulusofona.lp2.deisichess

object StatisticsKt {
    @JvmStatic
    fun getStatsCalculator(type: StatType):  Function1<GameManager,List<String>> {
        return when (type) {
            StatType.TOP_5_CAPTURAS -> ::calculateTop5Capturas
            StatType.TOP_5_PONTOS -> ::calculateTop5Pontos
            StatType.PECAS_MAIS_5_CAPTURAS -> ::calculatePecasMais5Capturas
            StatType.PECAS_MAIS_BARALHADAS -> ::calculatePecasMaisBaralhadas
            StatType.TIPOS_CAPTURADOS -> ::calculateTiposCapturados
        }
    }
    private fun calculateTop5Capturas(manager: GameManager): List<String> {
        val gamePieces = manager.board.getGamePieces()

        // Create a list of pieces with their capture counts
        val piecesWithCaptures = gamePieces.map { it.key to it.value.getCaptures() }

        // Sort the pieces based on their capture counts in descending order
        val sortedPieces = piecesWithCaptures.sortedByDescending { it.second }

        // Take the top 5 pieces if available
        val top5Pieces = sortedPieces.take(5)

        // Process top 5 pieces into a list of strings or format the data as needed
        val top5CapturesData = top5Pieces.mapNotNull { pieceIdCountPair ->
            val piece = manager.board.getPieceByID(pieceIdCountPair.first)
            val name = piece?.getName()
            val color = piece?.getColorString()
            val captures = pieceIdCountPair.second

            if (name != null && color != null) {
                "$name ($color) fez $captures capturas"
            } else { null }
        }


        return top5CapturesData
    }


    private fun calculateTop5Pontos(manager: GameManager): List<String> {
        val gamePieces = manager.board.gamePieces

        // Filter pieces with at least one capture and map them to a pair of ID and points obtained from captures
        val piecesWithCaptures = gamePieces
            .filter { it.value.getCaptures() > 0 }
            .map { it.key to it.value.getPointsObtainedFromCaptures() }

        // Sort the pieces based on points obtained from captures and then by name if points are the same
        val sortedPieces = piecesWithCaptures.sortedWith(compareByDescending<Pair<Int, Int>> { it.second }.thenBy
        { manager.board.getPieceByID(it.first)?.getName() })

        // Take the top 5 pieces if available
        val top5Pieces = sortedPieces.take(5)

        // Process top 5 pieces into a list of strings or format the data as needed
        val top5PontosData = top5Pieces.mapNotNull { pieceIdPointsPair ->
            val piece = manager.board.getPieceByID(pieceIdPointsPair.first)
            val name = piece?.getName()
            val color = piece?.getColorString()
            val points = pieceIdPointsPair.second

            if (name != null && color != null) {
                "$name ($color) tem $points pontos"
            } else { null }
        }

        return top5PontosData
    }


    private fun calculatePecasMais5Capturas(manager: GameManager): List<String> {
        val gamePieces = manager.board.getGamePieces()

        // Filter pieces with more than 5 captures
        val piecesWithMoreThan5Captures = gamePieces.filter { it.value.getCaptures() > 5 }

        // Process the filtered pieces into a list of strings
        val pecasMais5CapturasData = piecesWithMoreThan5Captures.mapNotNull { entry ->
            val piece = entry.value
            val name = piece.getName()
            val color = piece.getColorString()
            val captures = piece.getCaptures()

            if (name != null && color != null) {
                "$color:$name:$captures"
            } else { null }
        }

        return pecasMais5CapturasData
    }


    private fun calculatePecasMaisBaralhadas(manager: GameManager): List<String> {
        val gamePieces = manager.board.getGamePieces()

        // Filter pieces with at least one invalid move
        val filteredPieces = gamePieces.filterValues { piece ->
            val invalidMoves = piece.getMovimentosInvalidos()
            invalidMoves > 0 // Piece should have at least one invalid move
        }

        // Calculate the ratio of invalid moves to valid moves for each piece
        val piecesWithRatios = filteredPieces.map { entry ->
            val piece = entry.value
            val invalidMoves = piece.getMovimentosInvalidos().toDouble()
            val validMoves = piece.getMovimentosValidos().toDouble()

            entry.key to (invalidMoves / validMoves+invalidMoves) // Calculate the ratio
        }

        // Sort pieces based on the ratio of invalid moves to valid moves in descending order
        val sortedPieces = piecesWithRatios.toList().sortedByDescending { it.second }

        val top3Pieces = sortedPieces.take(3)

        // Construct the result with piece names, colors, invalid moves, and valid moves
        val result = top3Pieces.mapNotNull { (pieceId, ratio) ->
            val piece = manager.board.getPieceByID(pieceId)
            val name = piece?.getName()
            val color = piece?.getColor()
            val invalidMoves = piece?.getMovimentosInvalidos()
            val validMoves = piece?.getMovimentosValidos()

            if (name != null && color != null && invalidMoves != null && validMoves != null) {
                "$color:$name:$invalidMoves:$validMoves"
            } else { null }
        }

        return result
    }

    private fun calculateTiposCapturados(manager: GameManager): List<String> {
        val gamePieces = manager.board.getGamePieces()

        // Extract unique captured piece names using functional operations
        val uniqueCapturedPieceNames = gamePieces.values
            .filter { !it.getTasEmJogoFilho() }
            .map { it.getTypeName(manager.board) }
            .distinct()
            .sorted()

        return uniqueCapturedPieceNames
    }



}